<template>
    <div class="container mt-5 mb-5">
        <div class="d-flex justify-content-center row">
            <div class="col-md-10">
                <div class="row p-2 bg-white border rounded" v-for="(cotage, i) in Cottages" :key="i">
                    <div class="col-md-3 mt-1"><img class="img-fluid img-responsive rounded product-image"
                            :src="cotage.img_path"></div>
                    <div class="col-md-6 mt-1">
                        <h5>{{ cotage.name }}</h5>
                        <br />
                        <p class="text-justify para mb-0">{{cotage.description}}<br><br></p>
                    </div>
                    <div class="align-items-center align-content-center col-md-3 border-left mt-1">
                        <div class="d-flex flex-column mt-4">
                            <button class="btn btn-primary btn-sm" type="button">Detalji</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

export default {
    name: 'CardComponent',
    Cottages: {},
    data() {

        this.Cottages = [{ 'name': 'Dunav Vila', 'description': 'Ovo je opis Vile Dunav', 'img_path': require('../assets/vikendica1.jpg') },
        { 'name': 'Morava Vila', 'description': 'Ovo je neka druga vila', 'img_path': require('../assets/vikendica2.jpg') },
        { 'name': 'Vila Horizont', 'description': 'Vila Horizont nedaleko od Novog Sada, na prelepom vikend naselju.', 'img_path': require('../assets/vikendica3.jpg') },
        { 'name': 'Vila Vidikovac', 'description': 'Vila Vidikovac je pravi raj za uzivanje.', 'img_path': require('../assets/vikendica4.jpg') },
        { 'name': 'Vila na Kamenjaru', 'description': 'Vila na Kamenjaru. Dodjite nam.', 'img_path': require('../assets/vikendica5.jpg') },
        { 'name': 'Kuca na Klisi', 'description': 'Velika Kuca na Klisi', 'img_path': require('../assets/vikendica6.jpg') },
        { 'name': 'Vila Miseluk', 'description': 'Vila Miseluk', 'img_path': require('../assets/vikendica7.jpg') },
        { 'name': 'Rajska Vila', 'description': 'Rajska Vila za uzivanje kao u Raju', 'img_path': require('../assets/vikendica8.jpg') },
        { 'name': 'Kraljeva Vila', 'description': 'Kraljeva Vila za Kraljevski tretman', 'img_path': require('../assets/vikendica9.jpg') },
        { 'name': 'Apartmani Petrovaradin', 'description': 'Veoma lepi apartmani na Petrovaradinu, nadomak Novog Sada.', 'img_path': require('../assets/vikendica10.jpg') },
        { 'name': 'Vila Hills', 'description': 'Vila Hills za sve obozavatelje planinarenja.', 'img_path': require('../assets/vikendica6.jpg') }
        ]
        return this.Cottages
    }
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


