var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
var obj = {};
obj.header = header;

refreshTotalPrice();

$('.removeElement').click(function() {

	var self = this;

	$.ajax({
		url : "/zamowienia/utworz/" + $(this).data('id'),
		type : 'DELETE',
		headers : {
			'X-CSRF-TOKEN' : token
		}
	}).done(function() {

		$(self).parents('tr').nextAll().each(function() {
			
			$(this).find('button.removeElement').data('id', $(this).find('button.removeElement').data('id') - 1);
		});
		$(self).parents('tr').remove();
		refreshTotalPrice();

	});

});

function refreshTotalPrice() {
	
	var totalPrice = 0;
	
	$('#summary').find('tr').each(function() {
		totalPrice += $(this).find('.quantity').text() * $(this).find('.price').text();
	});
	
	$('#totalPrice').text(parseFloat(totalPrice).toFixed(2));
}