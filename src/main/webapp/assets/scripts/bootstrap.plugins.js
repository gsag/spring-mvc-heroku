/**
 * Created by guilherme on 10/02/16.
 * Bootstrap plugins
 */

$(document).ready(function() {
    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    /* Change active class for list-group-itens*/
    $('.list-group a').click(function(e) {
        $that = $(this);
        $('.list-group').find('a').removeClass('active');
        $that.addClass('active');
    });
});