$(function() {

	// utilities.
	function render(data) {
		var tmpl = $('#memo-tmpl').html();
		Mustache.parse(tmpl);
		var rendered = Mustache.render(tmpl, data);
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
			render(data);
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
		}).then(function(data, status, jqxhr) {
			render(data);
			var $date = $('.memo:first').find('.date');
			$date.html(format($date.html()));
			$('#txt').val('').focus();
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
			$('#modal').modal('hide');
			$memo.find('.txt p').html(data.memo.txt);
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