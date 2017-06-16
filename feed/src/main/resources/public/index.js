$(function() {
  function activate($link) {
    $('#sidebar').find('li').each(function() {
      $(this).removeClass('active');
    });
    $link.closest('li').addClass('active');
  }
  function loadFeed($link) {
    if ($(window).scrollTop() > 0) $(window).scrollTop(0);
    $('#main #title').text('更新中...');
    $('#main #feed').html('');
    $.ajax({
      url: '/feed', data: 'url=' +  $link.attr('href')
    }).done(function(data) {
      var feeds = '';
      var $items = $(data).find('item');
      for (var i=0; i<$items.length; i++) {
        if (i === 8) break;
        var $item = $($items[i]);
        feeds += '<li class="list-group-item">' +
          '<a href="' + $item.find('link').text() + '" target="_blank">' +
            $item.find('title').text() +
          '</a>' + 
        '</li>';
      };
      $('#main #feed').html(feeds);
      $('#main #title').text($link.text());
      $('#main #title').attr('href', $link.data('site-url'));
      $('.btn').removeClass('disabled');
      $('#btm-ctrl').removeClass('hidden');
    });
  }

  // onload: bind events ->
  $(document).on('click', '#sidebar>li>a', function(e) {
    e.preventDefault();
    $('.btn').addClass('disabled');
    $('#btm-ctrl').addClass('hidden');
    activate($(this));
    loadFeed($(this));
  });
  $('.next').click(function(e) {
  	e.preventDefault();
  	var $next = $('#sidebar').find('.active').next();
  	if (!$next[0]) $next = $('#sidebar').find('li').first();
  	$next.find('a').click();
  });
  $('.prev').click(function(e) {
  	e.preventDefault();
  	var $prev = $('#sidebar').find('.active').prev();
  	if (!$prev[0]) $prev = $('#sidebar').find('li').last();
  	$prev.find('a').click();
  });

  // onload: render sidebar ->
  $.ajax(
    'feeds.json'
  ).done(function(data) {
    var sb = "";
    $.each(data, function() {
      sb += '<li><a href="' + this.feedUrl + '" data-site-url="' + this.siteUrl + '">' + this.name + '</a></li>';
    });
    $('#sidebar').html(sb).find('a').first().click();
  });
});