var gulp = require('gulp');

gulp.task('dependency', function() {
	return gulp.src('bower_components/**')
		.pipe(gulp.dest('plugins/'));
});
