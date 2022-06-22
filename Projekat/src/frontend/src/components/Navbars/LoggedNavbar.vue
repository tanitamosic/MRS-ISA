<template>

    <nav class="navbar navbar-expand-sm">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <div class="navbar-nav me-auto order-0 nav-title navtxt">
                    <i><b>TurboJavaSpringbootTurizamExpo</b></i>
                </div>
                <div class="d-flex ms-auto order-5">
                    <router-link class="me-4 navtxt" to="/login">Odjava</router-link>
                </div>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    mounted() {
        let cookie = JSON.parse(sessionStorage.getItem('state'));
        let self = this;
        self.$store.accessToken = cookie.AccessToken;
        self.$store.role = cookie.Role;
        self.$store.username = cookie.Username;
        self.$store.currentPassword = cookie.Password;
        self.$store.lastPasswordResetDate = cookie.LastPasswordResetDate;
        self.$store.User = cookie.User
    }

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
    /* background-color: #0e7444; */
    background-color: #659dbd;
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
