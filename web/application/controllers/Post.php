<?php
	require APPPATH . '/libraries/REST_Controller.php';
	 
	class Post extends REST_Controller {
		
		function __construct()
		{
			// Construct the parent class
			parent::__construct();
			$this->load->model('mPost');
		}
		
		function post_user_get()
		{		
			
			if(!$this->get('idUser'))
			{
				$this->response(NULL, 400);
			}
			
			$posts = $this->mPost->getPostByIdUser($this->get('idUser'));
			
			if($posts)
			{
				$this->response($posts, 200); // 200 being the HTTP response code
			}
	 
			else
			{
				$this->response(NULL, 404);
			}
		}
		
		
		function post_get()
		{		
			
			if(!$this->get('idPost'))
			{
				$this->response(NULL, 400);
			}
			
			$post = $this->mPost->getPostById($this->get('idPost'));
			
			if($post)
			{
				$this->response($post, 200); // 200 being the HTTP response code
			}
	 
			else
			{
				$this->response(NULL, 404);
			}
		}
	
	}
?>