// 맛집 댓글 관련된 CRUD API 함수 파일
import { posts } from './index';

//맛집 댓글 데이터 리스트를 조회하는 API
function fetchPosts() {
  return posts.get('/');
}
// 특정 댓글 1개를 조회하는 API
function fetchPost(postId) {
  return posts.get(postId);
}
//맛집 댓글 데이터를 생성하는 API
function createPost(postData) {
  return posts.post('/', postData);
}

// 맛집 댓글 데이터를 삭제하는 API
function deletePost(postId) {
  return posts.delete(postId);
}

// 맛짓 댓글 수정하는 API
function editPost(postId, postData) {
  return posts.put(postId, postData);
}

export { fetchPosts, fetchPost, createPost, deletePost, editPost };
