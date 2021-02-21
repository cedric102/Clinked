<!doctype html>
<html lang="en">


<body>


  <table>
    <tr>
      <th style="padding-left: 20px;">ID</th>
      <th style="padding-left: 20px;">Title</th>
      <th style="padding-left: 20px;">Author</th>
      <th style="padding-left: 20px;">Content</th>
      <th style="padding-left: 20px;">Publiched Date</th>
    </tr>
    <!-- <c:forEach var="entry" items="${pageScope.articleList}">
      <tr><td><c.out value="${entry.id}"></c.out></td></tr>
    </c:forEach> -->
  </table>


  <!-- <div th:each="article1: ${articleListBetweenTimes}">
      <div style="display: inline-block;" th:text="${article1.key}"></div>
      <div style="display: inline-block;" th:text="${article1.value}"></div>
      <br>
    </div> -->


  <script>

    $("a.addToCart").click(function (e) {
      e.preventDefault();

      let $this = $(this);

      $this.next().removeClass("d-none");

      let id = $this.attr("data-id");
      let url = "/cart/add/" + id;

      $.get(url, {}, function (data) {
        $('div.cart').html(data);
      }).done(function () {
        $this.parent().parent().find('div.productAdded').fadeIn();
        $this.next().addClass('d-none');
        setTimeout(() => {
          $this.parent().parent().find('div.productAdded').fadeOut();
        }, 1000);
      });
    });


  </script>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://code.highcharts.com/highcharts.js"></script>
  <script src="https://code.highcharts.com/modules/exporting.js"></script>
  <script th:inline="javascript">

  </script>

</body>

</html>