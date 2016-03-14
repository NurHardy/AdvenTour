<?php
	require APPPATH . '/libraries/REST_Controller.php';
	 
	class User extends REST_Controller {
		
		function __construct()
		{
			// Construct the parent class
			parent::__construct();
			$this->load->model('mUser');
		}
	 
		function user_get()
		{
			if(!$this->get('username'))
			{
				$this->response(NULL, 400);
			}
		
			$user = $this->mUser->getUserByUsername($this->get('username'));
			
			if($user)
			{
				$this->response($user, 200); // 200 being the HTTP response code
			}
	 
			else
			{
				$this->response(NULL, 404);
			}
		}
		
		function users_get()
		{		
			$users = $this->mUser->getUsers();
			
			if($users)
			{
				$this->response($users, 200); // 200 being the HTTP response code
			}
	 
			else
			{
				$this->response(NULL, 404);
			}
		}
		
		function user_post()
		{
			$user = $this->mUser->getUserByUsername($this->get('username'));
			if(!user){
				$result = $this->mUser->insertUser(array(
					'username' => $this->post('username'),
					'email' => $this->post('email'),
					'password' => $this->post('password'),
					'name' => $this->post('name'),
					'exp' => $this->post('exp'),
					'level' => $this->post('level'),
					'token' => $this->post('name'),
					'photo' => $this->post('photo')
				));
				 
				if($result === FALSE)
				{
					$this->response(array('status' => 'failed'));
				}
				 
				else
				{
					$this->response(array('status' => 'success'));
				}
			}
			else{
				$result = $this->mUser->updateUser($this->post('idUser'), array(
					'username' => $this->post('username'),
					'email' => $this->post('email'),
					'password' => $this->post('password'),
					'name' => $this->post('name'),
					'exp' => $this->post('exp'),
					'level' => $this->post('level'),
					'token' => $this->post('name'),
					'photo' => $this->post('photo')
				));
				 
				if($result === FALSE)
				{
					$this->response(array('status' => 'failed'));
				}
				 
				else
				{
					$this->response(array('status' => 'success'));
				}
			}
		}
	}
?>