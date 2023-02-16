
import store from "../store/index"
// 权限按钮判断
const auth = {
    mounted(el, { value }) {
        store.state.authorityBtn.some(v => v.router == value) ? false : el.remove()
    }
}
export default auth