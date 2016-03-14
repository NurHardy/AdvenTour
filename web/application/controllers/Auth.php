<?php
	require APPPATH . '/libraries/REST_Controller.php';
	 
	class Auth extends REST_Controller {
	 
		function auth_get()
		{
			$this->load->model('mUser');
			
			if(!$this->get('username') || !$this->get('password'))
			{
				$this->response(NULL, 400);
			}
		
			$user = $this->mUser->getUserByUsername($this->get('username'));
			
			if($user)
			{
				//$this->response($username, 200); // 200 being the HTTP response code
				if ($this->get('password') == $user->password){
					$data = array('returned' => 'true');
					$this->response($data);
				}
			}
	 
			else
			{
				$this->response(NULL, 404);
			}
		}
	}
?>