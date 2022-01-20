<div class="content contact" id="menu-5">
    <div class="container">
        <div class="row">

            <h1> Customers</h1>

            <form action="allCustomersServlet">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="View all customers">
                </div>
            </form>
            <form action="editCustomerServlet">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="Edit customer">
                </div>
            </form>
            <form action="addCustomer.jsp">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="Add customer">
                </div>
            </form>
            <form action="findCustomerServlet">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="Find customer">
                </div>
            </form>


            <h1> Books</h1>

            <form action="allBooksServlet">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="View all books">
                </div>
            </form>
            <form action="allBooksServlet">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="Edit book">
                </div>
            </form>

            <h1> Catalog</h1>

            <form action="catalogServlet">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="View all books">
                </div>
            </form>
            <form action="prepareBooksToCatalogServlet">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="Add books to catalog">
                </div>
            </form>

            <h1> Cart </h1>

            <form action="cartServlet">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="View all cart">
                </div>
            </form>

            <h1> Orders</h1>

            <form action="orderServlet">
                <div class="col-sm-12">
                    <input class="send_btn" type="submit" value="View all orders">
                </div>
            </form>


        </div> <!-- /.row -->
    </div> <!-- /.container -->
</div> <!-- /.contact -->