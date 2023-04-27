<template>
  <div class="search-box">
    <Search></Search>
  </div>
</template>

<script>

import {mapState, mapMutations, mapGetters, osearch} from "vuex";
import Search from "@/vcomponent/Search";
export default {
  name: "Login",
  components: {
    Search,
  },
  computed: {
    ...mapState(['searchStatus']),
  },
  data() {
    return {};
  },
  created() {
  },
  mounted() {
  },
  methods: {

  },
};
</script>
<style lang="less" scoped>

</style>
