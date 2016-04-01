<?php
	class MUser extends CI_Model {

		private $idUser;
		private $username;
		private $email;
		private $password;
		private $name;
		private $exp;
		private $level;
		private $token;
		private $photo;

		public function __construct()
		{
				parent::__construct();
		}

		public function getUserByUsername($username)
		{
			$this->db->join('level', 'user.level = level.level');
			$query = $this->db->get_where('user', array('username' => $username));
			return $query->row();
		}
		
		public function getUsers()
		{
			$this->db->join('level', 'user.level = level.level');
			$query = $this->db->get('user');;
			return $query->result_array();
		}

		public function insertUser($username, $email, $password, $name, $exp, $level, $token, $photo)
		{
				$this->username	= $username;
				$this->email  	= $email;
				$this->password = $password;
				$this->name  	= $name;
				$this->exp  	= $exp;
				$this->level  	= $level;
				$this->token  	= $token;
				$this->photo  	= $photo;

				$this->db->insert('user', $this);
		}

		public function updateUser($idUser, $username, $email, $password, $name, $exp, $level, $token, $photo)
		{
				$this->username	= $username;
				$this->email  	= $email;
				$this->password = $password;
				$this->name  	= $name;
				$this->exp  	= $exp;
				$this->level  	= $level;
				$this->token  	= $token;
				$this->photo  	= $photo;
				
				$this->db->update('user', $this, array('idUser' => $idUser));
		}

	}
?>