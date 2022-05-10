<template>
    <div class="row p-2 bg-white border rounded">
        <div class="col-md-3 mt-1"><img class="img-fluid img-responsive rounded product-image" src='{{img_path}}'></div>
        <div class="col-md-6 mt-1">
            <h5>{{ name }}</h5>
            <br />
            <p class="text-justify para mb-0">{{ description }}<br><br></p>
        </div>
        <div class="align-items-center align-content-center col-md-3 border-left mt-1">
            <div class="d-flex flex-column mt-4">
                <button class="btn btn-primary btn-sm" type="button">Detalji</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'Card-Component',
    props: ['name', 'description', 'img_path']
}
</script>

<style scoped>
body {
    background: #eee
}

.ratings i {
    font-size: 16px;
    color: red
}

.strike-text {
    color: red;
    text-decoration: line-through
}

.product-image {
    width: 100%
}

.dot {
    height: 7px;
    width: 7px;
    margin-left: 6px;
    margin-right: 6px;
    margin-top: 3px;
    background-color: blue;
    border-radius: 50%;
    display: inline-block
}

.spec-1 {
    color: #938787;
    font-size: 15px
}

h5 {
    font-weight: 400
}

.para {
    font-size: 16px
}
</style>
