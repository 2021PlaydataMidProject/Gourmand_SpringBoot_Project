function saveAuthToCookie(value) {
  document.cookie = `til_auth=${value}`;
}

function saveUserIdToCookie(value) {
  document.cookie = `userId=${value}`;
}

function saveUserNumToCookie(value) {
  document.cookie = `userNum=${value}`;
}

function saveUserNameToCookie(value) {
  document.cookie = `userName=${value}`;
}

function getAuthFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)til_auth\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}

function getUserIdFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)userId\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}
function getUserNumFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)userNum\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}
function getUserNameFromCookie() {
  return document.cookie.replace(
    /(?:(?:^|.*;\s*)userName\s*=\s*([^;]*).*$)|^.*$/,
    '$1',
  );
}


function deleteCookie(value) {
  document.cookie = `userId=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
  document.cookie = `userNum=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
  document.cookie = `userName=; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
  document.cookie = `accessToken=; path=/; expires=Thu, 01 Jan 1970 00:00:01 GMT;`;
}

export {
  saveAuthToCookie,
  saveUserIdToCookie,
  saveUserNumToCookie,
  saveUserNameToCookie,

  getAuthFromCookie,
  getUserIdFromCookie,
  getUserNumFromCookie,
  getUserNameFromCookie,
  deleteCookie,

};
