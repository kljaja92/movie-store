(defproject movie-store "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [compojure "1.7.1"]
                 [ring/ring-core "1.12.2"]
                 [uncomplicate/neanderthal "0.49.1"]
                 [org.clojure/java.jdbc "0.7.12"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [de.ubercode.clostache/clostache "1.4.0"]
                 [ring/ring-jetty-adapter "1.12.2"]
                 [ring-basic-authentication "1.2.0"]]
  :plugins [[lein-ring "0.12.6"]
            [com.github.clj-kondo/lein-clj-kondo "0.2.5"]]
  :ring {:handler movie-store.core/app}
  :main ^:skip-aot movie-store.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}
             :dev {:dependencies [[midje "1.10.10"]
                                  [criterium "0.4.6"]]
                   :plugins [[lein-midje "3.2.2"]]}})