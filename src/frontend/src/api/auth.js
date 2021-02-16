// 로그인, 회원가입, (회원탈퇴)
import { instance } from './index';

// 회원가입 API
function registerUser(userData) {
  return instance.post('signup', userData);
}

// function registerUser(userData) {
//   const url = 'http://localhost:3000/signup';
//   return axios.post(url, userData);
// }

// 로그인 API
function loginUser(userData) {
  return instance.post('login', userData);
}

export { registerUser, loginUser };
