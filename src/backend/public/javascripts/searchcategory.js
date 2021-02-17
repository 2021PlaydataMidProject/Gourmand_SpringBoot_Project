// 실제로 활용되지 않는 코드입니다. 
// 테스트 코드 입니다.

const request = require('request')
const NAVER_CLIENT_ID     = 'jEsRY2tOdjqA0tAZRJcO'
const NAVER_CLIENT_SECRET = 'VcOeXZJm8m'
// var keyword = "진대감 서초점"

var keyword="진대감 서초점";
var keyword = encodeURI(keyword);

request.get({

  uri: `https://openapi.naver.com/v1/search/local.json?query=${keyword}&display=20&start=1&sort=random`,

  headers:{
    'X-Naver-Client-Id':NAVER_CLIENT_ID,
    'X-Naver-Client-Secret':NAVER_CLIENT_SECRET
  }
}, function(err, res, body) {
  let json = JSON.parse(body) //json으로 파싱
  console.log(json.items[0].category)
})

// '1', NULL, NULL, '서울 서초구 서초동 1594-3', NULL, '진대감 서초점', NULL, '37.48670', '127.0140'
