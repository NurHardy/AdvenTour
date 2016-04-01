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
			$query = $this->db->get('venue');;
			return $query->result_array();
		}

		public function insertUser()
		{
				$this->username	= $this->input->post('username');
				$this->email  	= $this->input->post('email');
				$this->password = $this->input->post('password');
				$this->name  	= $this->input->post('name');
				$this->exp  	= $this->input->post('exp');
				$this->level  	= $this->input->post('level');
				$this->token  	= $this->input->post('token');
				$this->photo  	= $this->input->post('photo');

				$this->db->insert('user', $this);
		}

		public function updateUser($idUser)
		{
				$this->username	= $this->input->post('username');
				$this->email  	= $this->input->post('email');
				$this->password = $this->input->post('password');
				$this->name  	= $this->input->post('name');
				$this->exp  	= $this->input->post('exp');
				$this->level  	= $this->input->post('level');
				$this->token  	= $this->input->post('token');
				$this->photo  	= $this->input->post('photo');
				
				$this->db->update('user', $this, array('idUser' => $idUser));
		}

	}
?>