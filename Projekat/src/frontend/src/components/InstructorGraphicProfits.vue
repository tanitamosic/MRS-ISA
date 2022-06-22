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
    components: {
        GChart
    },
    data() {
        return {
            chartDataYearly: [
                ['Godina', 'Godisnji profit' ],
                ['2022', 15740 ],
                ['2021', 11700 ],
                ['2020', 10700 ],
            ],
            chartDataMonthly: [
                ['Mesec', 'Profit 2022'],
                ['JAN', 1280], ['FEB', 970], ['MAR', 860], ['APR', 850], ['MAJ', 900],
                ['JUN', 1100], ['JUL', 1300], ['AVG', 1250], ['SEP', 1080], ['OKT', 800], ['NOV', 830], ['DEC', 920],

            ],
            chartDataWeekly: [
                ['Nedelja', 'Nedeljni profit'],
                ['1', 400],
                ['2', 480],
                ['3', 125],
                ['4', 175]
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