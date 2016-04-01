<?php
	require APPPATH . '/libraries/REST_Controller.php';
	 
	class Venue extends REST_Controller {
		
		function __construct()
		{
			// Construct the parent class
			parent::__construct();
			$this->load->model('mVenue');
		}
	 
		function venue_get()
		{
			if(!$this->get('idVenue'))
			{
				$this->response(NULL, 400);
			}
		
			$venue = $this->mVenue->getVenueById($this->get('idVenue'));
			
			if($venue)
			{
				$this->response($venue, 200); // 200 being the HTTP response code
			}
	 
			else
			{
				$this->response(NULL, 404);
			}
		}
		
		function venues_get()
		{		
			$venues = $this->mVenue->getVenues();
			
			if($venues)
			{
				$this->response($venues, 200); // 200 being the HTTP response code
			}
	 
			else
			{
				$this->response(NULL, 404);
			}
		}
}
?>