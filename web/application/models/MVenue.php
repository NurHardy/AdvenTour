<?php
	class MVenue extends CI_Model {

		private $idVenue;
		private $venueName;
		private $desription;
		private $popularity;
		private $category;
		private $cost;
		private $latitude;
		private $longitude;

		public function __construct()
		{
				parent::__construct();
		}

		public function getVenueById($idVenue)
		{
			$this->db->join('venue_photo', 'venue_photo.idVenue = venue.idVenue');
			$this->db->join('travel_journal', 'travel_journal.idVenue = venue.idVenue');
			$query = $this->db->get_where('venue', array('venue.idVenue' => $idVenue));
			return $query->row();
		}
		
		public function getVenues()
		{
			$this->db->join('venue_photo', 'venue_photo.idVenue = venue.idVenue');
			$query = $this->db->get('venue');;
			return $query->result_array();
		}

		public function getGoAdventour($time, $category, $budget)
		{
			$this->db->join('venue_photo', 'venue_photo.idVenue = venue.idVenue');
			$this->db->join('travel_journal', 'travel_journal.idVenue = venue.idVenue');
			$this->db->where('cost <=', $budget);
			$query = $this->db->get('venue');;
			return $query->result_array();
		}
	}
?>