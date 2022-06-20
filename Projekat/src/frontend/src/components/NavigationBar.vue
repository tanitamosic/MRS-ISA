<template>

    <nav class="navbar navbar-expand-sm">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="navbar-nav me-auto order-0 nav-title navtxt">
                   <i><b>TurboJavaSpringbootTurizamExpo</b></i>
                </div>
                <div class="d-flex ms-auto order-5">
                    <router-link class="me-4 navtxt" to="/" v-if="!$store.User" >Vikendice</router-link>
                    <router-link class="me-4 navtxt" to="/BoatsOverview" v-if="!$store.User" >Brodovi</router-link>
                    <router-link class="me-4 navtxt" to="/AdventureOverview" v-if="!$store.User" >Avanture</router-link>
                    <router-link class="me-4 navtxt" to="/login" v-if="!$store.User" >Prijava</router-link>
                    <router-link class="me-2 navtxt" to="/register" v-if="!$store.User" >Registracija</router-link>
                </div>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    
}
</script>

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
    z-index: 1;

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
