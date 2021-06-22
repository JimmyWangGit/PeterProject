function postComment() {
    var $input =$("#userComment");
    var comment = $input.val();
    var html=$("<li>").text(comment);
    html.prependTo('#comments');
    $.ajax({
        url: "http://localhost:8080/springPrj/add",
        data: {"comment": comment}
    })
}


