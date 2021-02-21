<!doctype html>
<html lang="en">
  
  <head th:replace="/fragments/head"></head>

  <body>
    
    <nav th:replace="/fragments/nav :: nav-admin"></nav>

    <table>
      <tr>
        <th style="padding-left: 20px;">ID</th>
        <th style="padding-left: 20px;">Title</th>
        <th style="padding-left: 20px;">Author</th>
        <th style="padding-left: 20px;">Content</th>
        <th style="padding-left: 20px;">Publiched Date</th>
      </tr>
      <tr th:each="article1: ${articleList}">
        <td style="padding-left: 20px;" th:text="${article1.id}"></td>
        <td style="padding-left: 20px;" th:text="${article1.Title}"></td>
        <td style="padding-left: 20px;" th:text="${article1.Author}"></td>
        <td style="padding-left: 20px;" th:text="${article1.Content}"></td>
        <td style="padding-left: 20px;" th:text="${article1.publishedDate}"></td>
      </tr>
    </table>

    <div style="color: red;" th:if="${AlreadyExists}">The Article Already Exists</div>
    <nav class="mt-3" th:if="${ count > perPage }">
      <ul class="pagination">
        <li class="page-item" th:if="${ page > 0 }">
          <a th:href="@{/articles/} + '?page=__${page-1}__'" class="page-link">Prev</a>
        </li>
        <li class="page-item" th:each="number: ${ #numbers.sequence( 0 , pageCount ) }" th:classappend="${page == number} ? 'active' : ''">
          <a th:href="@{/articles/} + '?page=__${number}__'" class="page-link" th:text="${number+1}"></a>
        </li>
        <li class="page-item" th:if="${ page < pageCount }">
          <a th:href="@{/articles/} + '?page=__${page+1}__'" class="page-link">Next</a>
        </li>
      </ul>
    </nav>

    <form method="post" th:object="${article}" th:action="@{/articles/add}">
      <div style="display: inline-block;">
        <label for="">Title</label>
        <div th:if="${titleWarning}" th:text="${titleWarning}" style="color: red;"></div>
        <input type="text" class="form-control" name="title" th:name="title" th:value="${title}" placeholder="Title">
      </div>
      <div style="display: inline-block;">
        <label for="">Author</label>
        <div th:if="${authorWarning}" th:text="${authorWarning}" style="color: red;"></div>
        <input type="text" class="form-control" name="author" th:name="author" th:value="${author}" placeholder="Author">
      </div>
      <div style="display: inline-block;">
        <label for="">Content</label>
        <div th:if="${contentWarning}" th:text="${contentWarning}" style="color: red;"></div>
        <textarea th:name="content" rows="5" name="content" class="form-control" th:text="${content}" placeholder="Content"></textarea>
      </div>
      <button class="btn btn-danger mb-5">Add</button>
    </form>
    
    <table>
      <tr>
        <th th:each="article1: ${articleListBetweenTimes}">
          <div style="padding-left: 20px;" th:text="${article1.key}"></div>
        </th>
      </tr>
      <tr>
        <td th:each="article1: ${articleListBetweenTimes}">
          <div style="padding-left: 20px;" th:text="${article1.value}"></div>
        </td>
      </tr>
    </table>

    </div>

    <footer th:replace="/fragments/footer"></footer>
      
    <script>

      $("a.addToCart").click( function (e) {
        e.preventDefault();

        let $this = $(this);

        $this.next().removeClass("d-none");

        let id = $this.attr("data-id");
        let url = "/cart/add/"+id;

        $.get(url , {} , function(data) {
          $('div.cart').html(data);
        }).done( function() {
          $this.parent().parent().find('div.productAdded').fadeIn();
          $this.next().addClass('d-none');
          setTimeout( () => {
            $this.parent().parent().find('div.productAdded').fadeOut();
          } , 1000 );
        });
      });

    </script>

  </body>
</html>

