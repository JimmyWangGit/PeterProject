function postComment() {
	//var comment = document().getElementById("#userComment").val();
	var comment=$("#userComment").val();

	$.ajax({
		url: "http://localhost:8080/comment/add",
		data: {
			"comment":comment
		},
		success: function(result) {
			console.log("Get here");
			$("<li>").text(comment).prependTo($("#comments"));
			//$("#comments").prependTo(comment);
			comment.val("");
		}
	});
}

