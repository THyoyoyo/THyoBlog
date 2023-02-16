import auth from "./auth"
const directives = {
    auth
}
export default {
    install(app) {
        Object.keys(directives).forEach((key) => {
            app.directive(key, directives[key])
        })
    },
}