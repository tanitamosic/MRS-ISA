<template>
    <div class="nav">
        <div class="nav-header">
            <div class="nav-title">
                Pecanje
            </div>
        </div>

        <div class="nav-links">
            <router-link to="/" id="registration-link">Pregled Vikendica</router-link>
            <router-link to="/register" id="registration-link">Registracija   </router-link>
        </div>
    </div>
</template>

<style scoped>
* {
    box-sizing: border-box;
}

body {
    font-family: 'segoe ui';
}

.nav {
    height: 50px;
    width: 100%;
    background-color: #33d466;
    position: relative;
}

.nav>.nav-header {
    display: inline;
}

.nav>.nav-header>.nav-title {
    display: inline-block;
    font-size: 22px;
    color: #fff;
    padding: 10px 10px 10px 10px;
}

.nav>.nav-links {
    display: inline;
    float: right;
    font-size: 18px;
    padding: 13px 10px 13px 10px;
    text-decoration: none;
    color: #efefef;
}

#registration-link {
    margin: 30px;
    color: #efefef;
    text-underline-offset: none;
}

</style>
