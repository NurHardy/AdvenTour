<?php
	class MPost extends CI_Model {

		private $idPost;
		private $idUser;
			
		public function getPostByIdUser($idUser)
		{
			$query = $this->db->query('
				SELECT post.idUser, username, posts.*, venueName FROM (
					SELECT idPost, idVenue, journal as content, time FROM travel_journal
						UNION
					SELECT idPost, idVenue, null as col3, time FROM check_in
						UNION
					SELECT idPost, idVenue, title, time FROM user_challenge, challenge
					WHERE user_challenge.idChallenge = challenge.idChallenge
					) as posts, post, venue, user
				WHERE posts.idPost = post.idPost
				AND posts.idVenue = venue.idVenue
				AND post.idUser = user.idUser
				');
			return $query->result_array();
		}
		
		public function getPostById($idPost)
		{
			$this->db->join('venue', 'venue.idVenue = thePost.idVenue');
			$this->db->join('post', 'post.idPost = thePost.idPost');
			$this->db->join('user', 'user.idUser = post.idUser');
			$this->db->join('like', 'like.idPost = thePost.idPost');
			$this->db->join('comment', 'comment.idPost = thePost.idPost');
			
			$postType = substr($idPost, 0, 2);
			if ($postType == 'jr'){
				$query = $this->db->get_where('travel_journal as thePost', array('thePost.idPost' => $idPost));
			}
			else if ($postType == 'ch'){
				$query = $this->db->get_where('check_in as thePost', array('thePost.idPost' => $idPost));
			}
			else {
				$query = $this->db->get_where('user_challenge as thePost', array('thePost.idPost' => $idPost));
			}
			return $query->row();
		}
	}
?>