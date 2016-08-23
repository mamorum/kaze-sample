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
  var sync =
    '-DsyncUrl=http://localhost:3000/__browser_sync__?method=reload';
  var dir = '-Dstatic.dir=src/main/resources/public'
  p = child.spawn('mvn.cmd', ['exec:java', sync, dir], ['']);
  p.stdout.on('data', function(b) { process.stdout.write(b.toString()); });
  p.stderr.on('data', function(b) { process.stderr.write(b.toString()); });
  gutil.log("App PID:" + p.pid);
});

gulp.task('bs-start', function() {
  bs.init({ // listen on localhost:3000
    proxy: "localhost:8080",
    open: false
  });
});
gulp.task('bs-reload', function() {
  bs.reload();
});

gulp.task('default', ['start-app', 'bs-start'], function() {
  // *.class compiled by IDE.
  gulp.watch('target/classes/**/*.class', ['stop-app', 'start-app']);
  // static contents published by jetty.
  gulp.watch('src/main/resources/public/**/*', ['bs-reload']);
  // logback-*.xml  and db/**/* are not watched.
});
