<?php
	require APPPATH . '/libraries/REST_Controller.php';
	 
	class Friend extends REST_Controller {
		
		function __construct()
		{
			// Construct the parent class
			parent::__construct();
			$this->load->model('mFriend');
		}
		
		function friend_get()
		{		
			
			if(!$this->get('idUser'))
			{
				$this->response(NULL, 400);
			}
			
			$friend = $this->mFriend->getFriend($this->get('idUser'));
			
			if($friend)
			{
				$this->response($friend, 200); // 200 being the HTTP response code
			}
	 
			else
			{
				$this->response(NULL, 404);
			}
		}
	
	}
?>