<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link href="css/stylex.css" rel="stylesheet">
  <link href="../css/stylex.css" rel="stylesheet">
  <style>
    .entry-trunk{height:auto; font-family: 'Roboto', sans-serif;}
    .formtext{font-weight:400;letter-spacing: 1.3px;}
    .inputtext{padding:8px 12px;font-size:16px;border:1px solid #aaa;border-radius:5px;width:800px;min-width:400px;
      color:#1a1a1a;}
    .formtextarea{height:130px;resize:none;}
    .formGenre,.formGenre-option{padding:8px 12px;width:400px;border:1px solid #aaa;border-radius:5px;color:#1a1a1a;
      font-family: 'Roboto', sans-serif;}
    .formsubmit{border:none;color:#fff; font-family: 'Roboto', sans-serif;font-size:16px;}
    .formcheckbox{background-color: #0d6efd;border-color: #0d6efd;}
  </style>
</head>
<body>
  <div class="entry-trunk flex borx">
    <form class="plr50 pt20 bor rel" action="admin?page=merchEdit" method="post">
        <span style="height:1px;" class="abs hidden">
            <input type="text" value="${singleMerch.id}" name="id" hidden><br/>
            <input type="text" value="${singleMerch.code}" name="code" hidden><br/>
            <input type="text" value="${singleMerch.image}" name="image" hidden><br/>
            <input type="text" value="${singleMerch.discount}" name="discount" hidden><br/>
            <input type="text" value="${singleMerch.sold_amount}" name="sold_amount" hidden><br/>
        </span>
      <h2>Edit Products</h2>
      <div class="pb15">
        <p class="formtext pb6">Product Title</p>
        <input type="text" class="inputtext" placeholder="Product Title" value="${singleMerch.title}" name="title">
      </div>
      <div class="pb15">
        <p class="formtext pb6">Product Description</p>
        <textarea class="inputtext formtextarea" placeholder="Product Desc" name="description">${singleMerch.title}</textarea>
      </div>
      <div class="pb15">
        <p class="formtext pb6">Game Title</p>
        <input type="text" class="inputtext" placeholder="Game Title" name="game" value="${singleMerch.game}">
      </div>
      <div class="pb15">
        <p class="formtext pb6">Price</p>
        <input type="text" class="inputtext" placeholder="Price" name="price" value="${singleMerch.price}">
      </div>
      <div class="pb15">
        <p class="formtext pb6">Quantity</p>
        <input type="text" class="inputtext" placeholder="Quantity" name="quantity" value="${singleMerch.quantity}">
      </div>
      <div class="pb15">
        <p class="formtext pb6">Genre</p>
        <select class="formGenre" name="genre">
          <option class="formGenre-option" value="action">Action</option>
          <option class="formGenre-option" value="adventure">Adventure</option>
          <option class="formGenre-option" value="rpg">RPG</option>
          <option class="formGenre-option" value="simulation">Simulation</option>
          <option class="formGenre-option" value="strategy">Strategy</option>
          <option class="formGenre-option" value="puzzle">Puzzle</option>
        </select>
      </div>
      <div class="pb15">
        <p class="formtext pb6">Type</p>
        <select class="formGenre" name="type">
          <option class="formGenre-option" value="tshirt">T-Shirt</option>
          <option class="formGenre-option" value="hoodie">Hoodie</option>
          <option class="formGenre-option" value="hat">Hat</option>
          <option class="formGenre-option" value="bag">Bag</option>
          <option class="formGenre-option" value="figure">Figure</option>
          <option class="formGenre-option" value="poster">Poster</option>
        </select>
      </div>
      <div class="pb15 flex bor">
        <c:choose>
            <c:when test="${singleMerch.featured == 'on'}">
                <!-- Code to execute when the condition is true -->
                <div class="formtext ptb3 pr10 bor">Featured</div>
                <input type="checkbox" class="formcheckbox mr50 borr" name="featured" checked>
            </c:when>
            <c:otherwise>
                <!-- Code to execute when the condition is false -->
                <div class="formtext ptb3 pr10 bor">Featured</div>
                <input type="checkbox" class="formcheckbox mr50 borr" name="featured">
            </c:otherwise>
        </c:choose>
                
        <c:choose>
            <c:when test="${singleMerch.latest == 'on'}">
                <!-- Code to execute when the condition is true -->
                <div class="formtext ptb3 pr10 bor">Latest</div>
                <input type="checkbox" class="formcheckbox borr" name="latest" checked>
            </c:when>
            <c:otherwise>
                <!-- Code to execute when the condition is false -->
                <div class="formtext ptb3 pr10 bor">Latest</div>
                <input type="checkbox" class="formcheckbox borr" name="latest">
            </c:otherwise>
        </c:choose>
      </div>
      <div class="pb15 none">
        <p class="formtext pb6">Image</p>
        <input type="file" class="" name="file"><br/>
      </div>
      <div class="pb15 mtb20">
        <input type="submit" class="formsubmit ptb6 plr30 br4 mr20 cup bgprimary" value="Update">
        <a href="admin?page=merchDelete&id=${singleMerch.id}" class="formsubmit ptb10 plr30 br4 mr20 cup bgdanger">Delete</a>
      </div>
    </form>
  </div>
</body>
</html>