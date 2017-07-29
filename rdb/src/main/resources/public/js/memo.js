$(function() {
  // utilities
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
    var d = new Date(msecString);
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
  function notify(res, $msg) {
    if (res.status === 400) {
      if (res.responseJSON.cause === "validate") {
        alert('Plese enter 200 characters or less.');
        $msg.css('color', 'red');
      }
    }
  }

  // onload
  (function() {
    $('#txt').focus();
    $.ajax({  // read
      url: '/memo', method: 'get', cache: false
    }).then(function(data, status, jqxhr) {
      renderToList(data);
      $('.date').each(function(index, e) {
        $(e).html(format($(e).html()));
      })
    });
  })();
  // create
  $('#create-btn').click(function() {
    var txt = $('#txt').val();
    if (txt === '') return;
    $.ajax({
      url: '/memo', data: {'txt':txt},
      method: 'post', cache: false
    }).done(function(data, status, jqxhr) {
      renderToList(data);
      var $date = $('.memo:first').find('.date');
      $date.html(format($date.html()));
      $('#txt').val('').focus();
    }).fail(function(data, status, jqxhr) {
      // TODO validation error
      //notify(data, $("#form-msg"));
    });
  });
  // update
  var $memo;
  $('body').on('click', '.edit', function() {
    $memo = $(this).closest('.memo');
    $('#new-txt').val($memo.find('.txt p').html());
    $('#modal').modal();
  });
  $('#modal-update').click(function() {
    var txt = $('#new-txt').val();
    var id = $memo.data('id');
    $.ajax({
      url: '/memo', method: 'put', cache: false,
      data: JSON.stringify({'id':id, 'txt':txt}),
      contentType: 'application/json'
    }).then(function(data, status, jqxhr) {
      $memo.html($(render(data)).html());
      var $date = $memo.find('.date');
      $date.html(format($date.html()));
      $('#modal').modal('hide');
    }).fail(function(data, status, jqxhr) {
      // TODO validation error
      //notify(data, $("#modal-msg"));
    });
  });
  // delete
  $('body').on('click', '.delete', function() {
    if (!confirm("Are you sure you want to delete?")) return;
    var $memo = $(this).closest('.memo');
    var url = '/memo/' + $memo.data('id');
    $.ajax({
      url: url, method: 'delete', cache: false
    }).then(function(data, status, jqxhr) {
      $memo.remove();
    });
  });
});