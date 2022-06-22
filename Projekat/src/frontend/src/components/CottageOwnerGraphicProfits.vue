<template>
    <div class="card shadow-lg border-dark" style="width: 1200px; height:700px">
        <div class="card-body">
            <GChart type="ColumnChart" :data="chartDataYearly" :options="chartOptions" />
            <GChart type="ColumnChart" :data="chartDataMonthly" :options="chartOptions" />
            <GChart type="ColumnChart" :data="chartDataWeekly" :options="chartOptions" />
        </div>
    </div>
</template>

<script>

import { GChart } from 'vue-google-charts'
export default {
    name: 'CottageOwnerReport',
    components: {
        GChart
    },
    data() {
        return {
            chartDataYearly: [
                ['Godina', 'Godisnji profit' ],
                ['2022', 13258 ],
                ['2021', 10700 ],
                ['2020', 9700 ],
            ],
            chartDataMonthly: [
                ['Mesec', 'Profit 2022'],
                ['JAN', 980], ['FEB', 420], ['MAR', 680], ['APR', 850], ['MAJ', 1100],
                ['JUN', 1300], ['JUL', 1500], ['AVG', 1550], ['SEP', 1280], ['OKT', 950], ['NOV', 780], ['DEC', 720],

            ],
            chartDataWeekly: [
                ['Nedelja', 'Nedeljni profit'],
                ['1', 300],
                ['2', 420],
                ['3', 375],
                ['4', 125]
            ],
            chartOptions: {
                chart: {
                    title: 'Chart Title',
                    subtitle: 'Sub Title',
                }
            }
        }
    }
}

</script>

<style scoped>
.card {
    margin-top: 100px;
    margin-left: 50px;
}

</style>