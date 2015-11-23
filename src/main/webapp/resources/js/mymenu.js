﻿
$(document).ready(function () {
    if ($(".adminControl").hasClass('active')) {
        $('.admin').fadeIn(300);
    }


    $(".adminControl").click(function () {

        if ($(this).hasClass('active')) {

            $.cookies.set('b_Admin_visibility', 'hidden');

            $('.admin').fadeOut(200);

            $(this).removeClass('active');

        } else {

            $.cookies.set('b_Admin_visibility', 'visible');

            $('.admin').fadeIn(300);

            $(this).addClass('active');
        }

    });
    $(".navigation .openable > a").click(function () {
        var par = $(this).parent('.openable');
        var sub = par.find("ul");

        if (sub.is(':visible')) {
            par.find('.popup').hide();
            par.removeClass('active');
        } else {
            par.addClass('active');
        }
        return false;
    });
});