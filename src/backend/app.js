
var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');


const cors = require('cors');
const expressSession = require('express-session');

var app = express();

// app.use(cookieParser());
// app.get('/mymap', function(req, resp) {
//    //console.log(req.cookies['userName']);
// })




// // 1) sessionStorage.getItem("user")받아왔을때 2) express-session -> 3) express-mysql-session 
// app.use(cors({
// 	origin: true, // 요청 주소와 같게
//   credentials: true, // cors, axios에서 둘 다 true로
// }));

// // app.use(session({ secret: 'somevalue' }));
// // process.env.COOKIE_SECRET
// app.use(expressSession({
//     resave: false, // 매번 session 강제 저장
//     saveUninitialized: false, // false할 경우 세션이 있든 없든 서버가 구동됨
//     secret: 'somevalue', // cookie 암호화 키. dotenv 라이브러리로 감춤
//     cookie: {
//       httpOnly: true, // javascript로 cookie에 접근하지 못하게 하는 옵션
//       secure: false, // https 프로토콜만 허락하는 지 여부
//     },
//     name: 'rnbshj', //cookie 이름을 변경하여 브라우저의 cookie 정보에서 cookie이름을 지워준다
// }));



// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

// express routers
app.use('/', indexRouter);
app.use('/users', usersRouter);


// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
