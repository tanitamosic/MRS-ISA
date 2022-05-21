<template>

    <nav class="navbar navbar-expand-sm">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="navbar-nav me-auto order-0 nav-title navtxt">
                   Turizam
                </div>
                <div class="d-flex ms-auto order-5">
                    <router-link class="me-2 navtxt" to="/" >Pregled Vikendica</router-link>

                    <router-link class="me-2 navtxt" to="/register">Registracija</router-link>
                </div>
            </div>
        </div>
    </nav>
</template>

<style scoped>
* {
    box-sizing: border-box;
}

body {
    font-family: "segoe ui";
}

nav {
    height: 50px;
    width: 100%;
    /* background-color: #33d466; */
    background-color: #8d8741;
    position: fixed;
    top: 0px;

    box-shadow: 0 1px 10px 0 rgba(0, 0, 0, 0.4);
}

.navtxt {
    color: white;
    font-size: 22px;
}

.navtxt:hover {
    color: #659dbd;
}

</style>
