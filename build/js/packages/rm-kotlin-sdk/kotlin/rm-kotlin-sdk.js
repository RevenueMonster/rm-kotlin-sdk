(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'ktor-ktor-client-core-js-legacy'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('ktor-ktor-client-core-js-legacy'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'rm-kotlin-sdk'.");
    }if (typeof this['ktor-ktor-client-core-js-legacy'] === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk'. Its dependency 'ktor-ktor-client-core-js-legacy' was not found. Please, check whether 'ktor-ktor-client-core-js-legacy' is loaded prior to 'rm-kotlin-sdk'.");
    }root['rm-kotlin-sdk'] = factory(typeof this['rm-kotlin-sdk'] === 'undefined' ? {} : this['rm-kotlin-sdk'], kotlin, this['ktor-ktor-client-core-js-legacy']);
  }
}(this, function (_, Kotlin, $module$ktor_ktor_client_core_js_legacy) {
  'use strict';
  var $$importsForInline$$ = _.$$importsForInline$$ || (_.$$importsForInline$$ = {});
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var COROUTINE_SUSPENDED = Kotlin.kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED;
  var CoroutineImpl = Kotlin.kotlin.coroutines.CoroutineImpl;
  var HttpResponse = $module$ktor_ktor_client_core_js_legacy.io.ktor.client.statement.HttpResponse;
  var getKClass = Kotlin.getKClass;
  var createKType = Kotlin.createKType;
  var print = Kotlin.kotlin.io.print_s8jyv4$;
  var Exception = Kotlin.kotlin.Exception;
  var Unit = Kotlin.kotlin.Unit;
  var takeFrom = $module$ktor_ktor_client_core_js_legacy.$$importsForInline$$['ktor-ktor-http-js-legacy'].io.ktor.http.takeFrom_jl1sg7$;
  var utils = $module$ktor_ktor_client_core_js_legacy.io.ktor.client.utils;
  var url = $module$ktor_ktor_client_core_js_legacy.io.ktor.client.request.url_3rzbk2$;
  var HttpMethod = $module$ktor_ktor_client_core_js_legacy.$$importsForInline$$['ktor-ktor-http-js-legacy'].io.ktor.http.HttpMethod;
  var HttpRequestBuilder_init = $module$ktor_ktor_client_core_js_legacy.io.ktor.client.request.HttpRequestBuilder;
  var HttpStatement_init = $module$ktor_ktor_client_core_js_legacy.io.ktor.client.statement.HttpStatement;
  var throwCCE = Kotlin.throwCCE;
  var equals = Kotlin.equals;
  var complete = $module$ktor_ktor_client_core_js_legacy.io.ktor.client.statement.complete_abn2de$;
  var reflect = $module$ktor_ktor_client_core_js_legacy.$$importsForInline$$['ktor-ktor-utils-js-legacy'].io.ktor.util.reflect;
  var typeInfoImpl = $module$ktor_ktor_client_core_js_legacy.$$importsForInline$$['ktor-ktor-utils-js-legacy'].io.ktor.util.reflect.typeInfoImpl_1lvkm8$;
  var HttpClient = $module$ktor_ktor_client_core_js_legacy.io.ktor.client.HttpClient_f0veat$;
  var lazy = Kotlin.kotlin.lazy_klfg04$;
  function MerchantModule() {
  }
  MerchantModule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MerchantModule',
    interfaces: []
  };
  function get$lambda($receiver) {
    return Unit;
  }
  function PaymentModule(revenueMonsterSDK) {
    this.revenueMonsterSDK_0 = revenueMonsterSDK;
  }
  function Coroutine$qrPay($this, continuation_0) {
    CoroutineImpl.call(this, continuation_0);
    this.exceptionState_0 = 11;
    this.$this = $this;
    this.local$response = void 0;
  }
  Coroutine$qrPay.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: null,
    interfaces: [CoroutineImpl]
  };
  Coroutine$qrPay.prototype = Object.create(CoroutineImpl.prototype);
  Coroutine$qrPay.prototype.constructor = Coroutine$qrPay;
  Coroutine$qrPay.prototype.doResume = function () {
    do
      try {
        switch (this.state_0) {
          case 0:
            this.exceptionState_0 = 9;
            var $receiver_0 = this.$this.revenueMonsterSDK_0.client;
            var urlString = 'https://ktor.io/';
            var host_0;
            var body_0;
            host_0 = 'localhost';
            body_0 = utils.EmptyContent;
            var $receiver_1 = new HttpRequestBuilder_init();
            url($receiver_1, 'http', host_0, 0, '/');
            $receiver_1.method = HttpMethod.Companion.Get;
            $receiver_1.body = body_0;
            takeFrom($receiver_1.url, urlString);
            get$lambda($receiver_1);
            var $this_0 = new HttpStatement_init($receiver_1, $receiver_0);
            var tmp$_4, tmp$_5, tmp$_6;
            tmp$_4 = getKClass(HttpResponse);
            if (equals(tmp$_4, getKClass(HttpStatement_init))) {
              this.result_0 = Kotlin.isType(tmp$_5 = $this_0, HttpResponse) ? tmp$_5 : throwCCE();
              this.state_0 = 8;
              continue;
            } else {
              if (equals(tmp$_4, getKClass(HttpResponse))) {
                this.state_0 = 6;
                this.result_0 = $this_0.execute(this);
                if (this.result_0 === COROUTINE_SUSPENDED)
                  return COROUTINE_SUSPENDED;
                continue;
              } else {
                this.state_0 = 1;
                this.result_0 = $this_0.executeUnsafe(this);
                if (this.result_0 === COROUTINE_SUSPENDED)
                  return COROUTINE_SUSPENDED;
                continue;
              }
            }

          case 1:
            this.local$response = this.result_0;
            this.exceptionState_0 = 4;
            var tmp$_7;
            var tmp$_8 = this.local$response.call;
            var typeInfo$result_0;
            typeInfo$break: do {
              try {
                typeInfo$result_0 = typeInfoImpl(reflect.JsType, getKClass(HttpResponse), createKType(getKClass(HttpResponse), [], false));
              } catch (_) {
                typeInfo$result_0 = typeInfoImpl(reflect.JsType, getKClass(HttpResponse), null);
                break typeInfo$break;
              }
            }
             while (false);
            this.state_0 = 2;
            this.result_0 = tmp$_8.receive_qi9ur9$(typeInfo$result_0, this);
            if (this.result_0 === COROUTINE_SUSPENDED)
              return COROUTINE_SUSPENDED;
            continue;
          case 2:
            this.result_0 = Kotlin.isType(tmp$_7 = this.result_0, HttpResponse) ? tmp$_7 : throwCCE();
            this.exceptionState_0 = 9;
            this.finallyPath_0 = [3];
            this.state_0 = 5;
            continue;
          case 3:
            this.state_0 = 7;
            continue;
          case 4:
            this.finallyPath_0 = [9];
            this.state_0 = 5;
            continue;
          case 5:
            this.exceptionState_0 = 9;
            complete(this.local$response);
            this.state_0 = this.finallyPath_0.shift();
            continue;
          case 6:
            this.result_0 = Kotlin.isType(tmp$_6 = this.result_0, HttpResponse) ? tmp$_6 : throwCCE();
            this.state_0 = 7;
            continue;
          case 7:
            this.state_0 = 8;
            continue;
          case 8:
            this.result_0;
            var response = this.result_0;
            print(response);
            this.exceptionState_0 = 11;
            this.state_0 = 10;
            continue;
          case 9:
            this.exceptionState_0 = 11;
            var e = this.exception_0;
            if (Kotlin.isType(e, Exception)) {
              print(e.message);
            } else
              throw e;
            this.state_0 = 10;
            continue;
          case 10:
            return;
          case 11:
            throw this.exception_0;
          default:this.state_0 = 11;
            throw new Error('State Machine Unreachable execution');
        }
      } catch (e) {
        if (this.state_0 === 11) {
          this.exceptionState_0 = this.state_0;
          throw e;
        } else {
          this.state_0 = this.exceptionState_0;
          this.exception_0 = e;
        }
      }
     while (true);
  };
  PaymentModule.prototype.qrPay = function (continuation_0, suspended) {
    var instance = new Coroutine$qrPay(this, continuation_0);
    if (suspended)
      return instance;
    else
      return instance.doResume(null);
  };
  PaymentModule.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PaymentModule',
    interfaces: []
  };
  function RevenueMonsterSDK() {
    this.payment = null;
    this.merchant = null;
    this.client_cotmzk$_0 = lazy(RevenueMonsterSDK$client$lambda);
    this.payment = new PaymentModule(this);
    this.merchant = new MerchantModule();
  }
  Object.defineProperty(RevenueMonsterSDK.prototype, 'client', {
    configurable: true,
    get: function () {
      return this.client_cotmzk$_0.value;
    }
  });
  RevenueMonsterSDK.prototype.getString = function () {
    return 'test';
  };
  function RevenueMonsterSDK$client$lambda() {
    return HttpClient();
  }
  RevenueMonsterSDK.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RevenueMonsterSDK',
    interfaces: []
  };
  var package$org = _.org || (_.org = {});
  var package$rm = package$org.rm || (package$org.rm = {});
  var package$sdk = package$rm.sdk || (package$rm.sdk = {});
  package$sdk.MerchantModule = MerchantModule;
  $$importsForInline$$['ktor-ktor-client-core-js-legacy'] = $module$ktor_ktor_client_core_js_legacy;
  package$sdk.PaymentModule = PaymentModule;
  package$sdk.RevenueMonsterSDK = RevenueMonsterSDK;
  Kotlin.defineModule('rm-kotlin-sdk', _);
  return _;
}));

//# sourceMappingURL=rm-kotlin-sdk.js.map
