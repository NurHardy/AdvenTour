<?php
	class MFriend extends CI_Model {

		private $idUser;
		private $idFriend;
			
		public function getFriend($idUser)
		{
			$this->db->where('friend.idUser =', $idUser);
			$this->db->join('user', 'user.idUser = friend.idFriend');
			$this->db->join('level', 'user.level = level.level');
			$query = $this->db->get('friend');;
			return $query->result_array();
		}
	}
?>