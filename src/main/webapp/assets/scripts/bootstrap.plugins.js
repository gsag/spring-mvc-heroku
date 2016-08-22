/**
 * Created by guilherme on 10/02/16.
 * Bootstrap plugins
 */

$(document).ready(function() {
    "use strict";

    // Activate tooltip
    $('[data-toggle="tooltip"]').tooltip();

    /* Change active class for list-group-itens*/
    $('.list-group > .list-group-item').click(function(e) {
        e.preventDefault();
        $('.list-group').find('.list-group-item').removeClass('active');
        $(this).addClass('active');
    });

    /* Thymeleaf's partial fragment ajax rendering */
    $(".js-profile-group-menu > .list-group-item").click(function () {
        $("#profile-content-partial").load($(this).data("url"));
    });
});