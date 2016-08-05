var
  gulp = require('gulp'),
  child = require('child_process'),
  gutil = require('gulp-util'),
  lr = require('gulp-livereload'),
  wait = require('gulp-wait2');

var p;

gulp.task('stop', function() {
  child.exec('taskkill /pid ' + p.pid + ' /f /t');
});

gulp.task('start', function() {
  p = child.spawn(
    'C:\\gradle-2.10\\bin\\gradle.bat',
    ['run'], ['']
  );
  p.stdout.on('data', function(b) { process.stdout.write(b.toString()); });
  p.stderr.on('data', function(b) { process.stderr.write(b.toString()); });
  gutil.log("PID:" + p.pid);
});

gulp.task('default', ['start'], function() {
  lr.listen();
  gulp.watch('src/**/*', ['stop', 'start']);
});


// for livereload -->

// gulp.task('reload', function() {
//   setTimeout(function() {lr.reload('*');}, 10000);
// });

// gulp.task('default', ['start'], function() {
//   lr.listen();
//   gulp.watch('src/**/*', ['stop', 'start', 'reload']);
// });

// and livereload.js is needed in html.
// <script src="http://localhost:35729/livereload.js"></script>