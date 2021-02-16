var express = require('express');
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


router.post('/location', function (req, res) {
  console.log(req.body);
  var restaurant = {
    'res_name': req.body.title,
    // 'tel': req.body
    'res_address': req.body.address,
    'x_value': req.body.lat,
    'y_value': req.body.lng,
  };
  connection.query('insert into res set ?', restaurant, function (err, result) {
    if (err) {
      console.error(err);
      throw err;
    }
    res.status(200).send('success');
  });
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


router.delete('/location', function (req, res) {
  var sql = 'DELETE FROM res WHERE res_name="?"';
  var params = [6]; // 숫자는 없앨 id 값을 넣으면 된다.
  connection.query(sql, params, function(err, rows, fields){
    if(err) console.log(err);
    console.log(rows);
  });
});

// var sql = 'DELETE FROM topic WHERE res_num="?"';
// var params = [6]; // 숫자는 없앨 id 값을 넣으면 된다.
// connection.query(sql, params, function(err, rows, fields){
//     if(err) console.log(err);
//     console.log(rows);
// });

module.exports = router;
