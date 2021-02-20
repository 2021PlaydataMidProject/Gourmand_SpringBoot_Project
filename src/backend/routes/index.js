var express = require('express');
var request = require('request'); //category 가져오기
var router = express.Router();
// const locationModel = require("../model/location");



// 맛집 등록 확인
router.get("/mymap", (req, res, next) => {
  res.render("index", { title: "Express" });
});
// 맛집 등록
router.get("/upload", (req, res, next) => {
  res.render("upload");
});
// 맛집 검색
router.get('/search', function(req, res, next) {
  res.render('search', { title: 'Express' });
});

// <----------------------------- MYSQL DB CONNECTION ----------------------------->
var mysql = require('mysql');
// Connection 객체 생성 
var connection = mysql.createConnection({
  host: 'localhost',
  port: 3306,
  user: 'root',   
  password: '0000',
  database: 'gourmand'  
});  
// Connect
connection.connect(function (err) {   
  if (err) {     
    console.error('mysql connection error');     
    console.error(err);     
    throw err;   
  } 
});


// <----------------------------- 네이버 지도 api ----------------------------->
router.post('/location', function (req, res) {
  // 네이버 지역 검색 API - 음식점명으로 category 받아오기
  const NAVER_CLIENT_ID     = 'jEsRY2tOdjqA0tAZRJcO'
  const NAVER_CLIENT_SECRET = 'VcOeXZJm8m'

  var keyword = req.body.title;
  var keyword = encodeURI(keyword);

  request.get({

    uri: `https://openapi.naver.com/v1/search/local.json?query=${keyword}&display=20&start=1&sort=random`,

    headers:{
      'X-Naver-Client-Id':NAVER_CLIENT_ID,
      'X-Naver-Client-Secret':NAVER_CLIENT_SECRET
    }
  }, function (err, res, body) {
    let json = JSON.parse(body) //json으로 파싱
    //console.log(json.items[0].category)
    categories = json.items[0].category // 음식점>카페,디저트
    req.body.category = categories; 
    console.log(req.body.category);
    let restaurant = {
      'res_name': req.body.title,
      'category': req.body.category,
      'res_address': req.body.address,
      'x_value': req.body.lat,
      'y_value': req.body.lng,
    };
    connection.query('insert into res set ?', restaurant, function (err, result) {
      if (err) {
        console.error(err);
        throw err;
        }
    });
  })
  res.status(200).send('success');
});
  


router.get('/location', function (req, res) {
  connection.query('SELECT res_address, res_name, x_value, y_value FROM res', function (err, rows) {
    if (err) throw err;
    console.log(rows)
    res.json({
        message: "success",
        data: rows,
    });
  });
});


// DB에 등록된 하나의 특정 맛집 삭제
router.delete('/location', function (req, res) {

  let sql = 'DELETE FROM res WHERE ?';
  let res_keyword = {
    'res_name':req.body.title,
  };
  
  connection.query(sql, res_keyword, function(err){
    if(err) {
      console.log("Error delete: " + err);
      throw err
    }
  });
  res.status(200).send('success');
});

// DB에 등록된 맛집 모두 삭제
router.delete('/location/delete/all', function (req, res) {
  var sql = 'DELETE FROM res';

  connection.query(sql, function(err){
    if(err) {
      console.log("Error all delete: " + err);
      throw err
    }
  });
  res.status(200).send('success');
});


module.exports = router;
