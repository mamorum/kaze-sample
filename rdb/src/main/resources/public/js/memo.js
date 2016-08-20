$(function() {

	// utilities.
	function render(data) {
		var tmpl = $('#memo-tmpl').html();
		Mustache.parse(tmpl);
		return Mustache.render(tmpl, data);
	}
	function renderToList(data) {
		var rendered = render(data);
		$('#memo-list').prepend(rendered);
	}
	function format(msecString) {
		var d = new Date(Number(msecString));
		if (d.toLocaleDateString() === (new Date()).toLocaleDateString()) {
			// today. return 'hh:mm'.
			return ('0' + d.getHours()).slice(-2) + ':' +
						('0' + d.getMinutes()).slice(-2);
		}
		// not today. return 'yyyy.mm.dd'.
		return d.getFullYear() + '.' +
					('0' + (d.getMonth()+1)).slice(-2) + '.' +
					('0' + d.getDate()).slice(-2);
	}


	// onload.
	(function() {

		$('#txt').focus();

		// read.
		$.ajax({
			url: '/memo',
			method: 'get',
			cache: false
		}).then(function(data, status, jqxhr) {
			renderToList(data);
			$('.date').each(function(index, e) {
				$(e).html(format($(e).html()));
			})
		});
	})();

	
	// create.
	$('#create').click(function() {

		var txt = $('#txt').val();
		if (txt === '') return;

		$.ajax({
			url: '/memo',
			data: JSON.stringify({'txt':txt}),
			contentType: 'application/json',
			method: 'post',
			cache: false
		}).done(function(data, status, jqxhr) {
			renderToList(data);
			var $date = $('.memo:first').find('.date');
			$date.html(format($date.html()));
			$("#form-msg").css('color', '#888');
			$('#txt').val('').focus();
		}).fail(function(data, status, jqxhr) {
			if (data.status === 400) {
				if (data.responseJSON.cause === "validate") {
					$("#form-msg").fadeOut(500, function(){
						$("#form-msg").css('color', 'red');
						$(this).fadeIn(500)
					});
				}
			}
		});
	});

	// update.
	var $memo;
	$('body').on('click', '.edit', function() {
		$memo = $(this).closest('.memo');
		$('#new-txt').val($memo.find('.txt p').html());
		$('#modal').modal();
	});
	$('#modal-update').click(function() {

		var txt = $('#new-txt').val();
		var url = '/memo/' + $memo.data('id');

		$.ajax({
			url: url,
			data: JSON.stringify({'txt':txt}),
			contentType: 'application/json',
			method: 'put',
			cache: false
		}).then(function(data, status, jqxhr) {
			$memo.html($(render(data)).html());
			var $date = $memo.find('.date');
			$date.html(format($date.html()));
			$('#modal').modal('hide');
		});
	});

	// delete.
	$('body').on('click', '.delete', function() {

		if (!confirm("Are you sure you want to delete?")) return;

		var $memo = $(this).closest('.memo');
		var url = '/memo/' + $memo.data('id');

		$.ajax({
			url: url,
			method: 'delete',
			cache: false
		}).then(function(data, status, jqxhr) {
			$memo.remove();
		});
	});
});