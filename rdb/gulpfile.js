var
  p,  // process
  child = require('child_process'),
  gulp = require('gulp'),
  gutil = require('gulp-util'),
  bs = require('browser-sync').create();

// TODO Linux にも対応する
gulp.task('stop-app', function() {
  child.exec('taskkill /pid ' + p.pid + ' /f /t');
});
gulp.task('start-app', function() {
  p = child.spawn(
    'mvn.cmd', ['exec:java'], ['']
  );
  p.stdout.on('data', function(b) { process.stdout.write(b.toString()); });
  p.stderr.on('data', function(b) { process.stderr.write(b.toString()); });
  gutil.log("App PID:" + p.pid);
});

gulp.task('bs-start', function() {
  bs.init({
    // BrowserSync waits on localhost:3000.
    proxy: "localhost:8080",
    open: true
  });
});

gulp.task('default', ['start-app', 'bs-start'], function() {
  // gulp.watch('src/**/*', ['stop-app', 'start-app']);

  // Watch *.class compiled by IDE.
  gulp.watch('target/classes/**/*', ['stop-app', 'start-app']);
});
