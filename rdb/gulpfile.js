var
  p,  // process
  child = require('child_process'),
  gulp = require('gulp'),
  gutil = require('gulp-util'),
  livereload = require('gulp-livereload');


// Linux にも対応する
gulp.task('stop-app', function() {
  child.exec('taskkill /pid ' + p.pid + ' /f /t');
});

gulp.task('start-app', function() {
  p = child.spawn(
    'C:\\gradle-2.10\\bin\\gradle.bat',
    ['run'], ['']
  );
  p.stdout.on('data', function(b) { process.stdout.write(b.toString()); });
  p.stderr.on('data', function(b) { process.stderr.write(b.toString()); });
  gutil.log("App PID:" + p.pid);
});

gulp.task('start-livereload', function() {
  livereload.listen();  // port=35729
});

gulp.task('default', ['start-app', 'start-livereload'], function() {
  gulp.watch('src/**/*', ['stop-app', 'start-app']);
});
