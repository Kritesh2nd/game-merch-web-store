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
  <div class="entry-trunk flex bor">
    <form class="plr50 pt20 bor" action="admin?page=merchAdd" method="post">
        <h2>Add Products</h2>
      <div class="pb15">
        <p class="formtext pb6">Product Title</p>
        <input type="text" class="inputtext" placeholder="Product Title" name="title">
      </div>
      <div class="pb15">
        <p class="formtext pb6">Product Description</p>
        <textarea class="inputtext formtextarea" placeholder="Product Desc" name="description"></textarea>
      </div>
      <div class="pb15">
        <p class="formtext pb6">Game Title</p>
        <input type="text" class="inputtext" placeholder="Game Title" name="game">
      </div>
      <div class="pb15">
        <p class="formtext pb6">Price</p>
        <input type="text" class="inputtext" placeholder="Price" name="price">
      </div>
      <div class="pb15">
        <p class="formtext pb6">Quantity</p>
        <input type="text" class="inputtext" placeholder="Quantity" name="quantity">
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
        <div class="formtext ptb3 pr10 bor">Featured</div>
        <input type="checkbox" class="formcheckbox mr50 borr" name="featured">
        <div class="formtext ptb3 pr10 bor">Latest</div>
        <input type="checkbox" class="formcheckbox borr" name="latest">
      </div>
      <div class="pb15 none">
        <p class="formtext pb6">Image</p>
        <input type="file" class="" name="file"><br/>
      </div>
      

      <div class="pb15 mtb20">
        <input type="submit" class="formsubmit ptb6 plr30 br4 mr20 cup bgprimary" value="Submit">
        <input type="reset" class="formsubmit ptb6 plr30 br4 mr20 cup bgdanger" value="Reset">
      </div>
    </form>
  </div>
</body>
</html>