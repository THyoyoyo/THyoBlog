<template >
  <div class="calendar">
    <table>
      <thead>
        <tr>
          <th>一</th>
          <th>二</th>
          <th>三</th>
          <th>四</th>
          <th>五</th>
          <th>六</th>
          <th>七</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(item, key) in 5" :key="key">
          <td v-for="(item2, key2) in key * 7" :key="key2">{{ item2 }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>
export default {};
</script>
<style lang="less" scoped>
.calendar {
  table {
    thead {
      tr {
        th {
          width: 100px;
          line-height: 30px;
        }
      }
    }
    tbody {
      tr {
        td {
          height: 60px;
          text-align: center;
        }
      }
    }
  }
}
</style>