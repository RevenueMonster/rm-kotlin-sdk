(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'rm-kotlin-sdk', 'kotlin-test', 'kotlinx-coroutines-core'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('rm-kotlin-sdk'), require('kotlin-test'), require('kotlinx-coroutines-core'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk-test'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'rm-kotlin-sdk-test'.");
    }if (typeof this['rm-kotlin-sdk'] === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk-test'. Its dependency 'rm-kotlin-sdk' was not found. Please, check whether 'rm-kotlin-sdk' is loaded prior to 'rm-kotlin-sdk-test'.");
    }if (typeof this['kotlin-test'] === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk-test'. Its dependency 'kotlin-test' was not found. Please, check whether 'kotlin-test' is loaded prior to 'rm-kotlin-sdk-test'.");
    }if (typeof this['kotlinx-coroutines-core'] === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk-test'. Its dependency 'kotlinx-coroutines-core' was not found. Please, check whether 'kotlinx-coroutines-core' is loaded prior to 'rm-kotlin-sdk-test'.");
    }root['rm-kotlin-sdk-test'] = factory(typeof this['rm-kotlin-sdk-test'] === 'undefined' ? {} : this['rm-kotlin-sdk-test'], kotlin, this['rm-kotlin-sdk'], this['kotlin-test'], this['kotlinx-coroutines-core']);
  }
}(this, function (_, Kotlin, $module$rm_kotlin_sdk, $module$kotlin_test, $module$kotlinx_coroutines_core) {
  'use strict';
  var RevenueMonsterSDK = $module$rm_kotlin_sdk.org.rm.sdk.RevenueMonsterSDK;
  var assertEquals = $module$kotlin_test.kotlin.test.assertEquals_3m0tl5$;
  var coroutines = $module$kotlinx_coroutines_core.kotlinx.coroutines;
  var CoroutineScope = $module$kotlinx_coroutines_core.kotlinx.coroutines.CoroutineScope_1fupul$;
  var COROUTINE_SUSPENDED = Kotlin.kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED;
  var CoroutineImpl = Kotlin.kotlin.coroutines.CoroutineImpl;
  var launch = $module$kotlinx_coroutines_core.kotlinx.coroutines.launch_s496o7$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var test = $module$kotlin_test.kotlin.test.test;
  var suite = $module$kotlin_test.kotlin.test.suite;
  function RevenueMonsterSDKTest() {
  }
  function Coroutine$RevenueMonsterSDKTest$initSDK$lambda(closure$sdk_0, $receiver_0, controller, continuation_0) {
    CoroutineImpl.call(this, continuation_0);
    this.$controller = controller;
    this.exceptionState_0 = 1;
    this.local$closure$sdk = closure$sdk_0;
  }
  Coroutine$RevenueMonsterSDKTest$initSDK$lambda.$metadata$ = {
    kind: Kotlin.Kind.CLASS,
    simpleName: null,
    interfaces: [CoroutineImpl]
  };
  Coroutine$RevenueMonsterSDKTest$initSDK$lambda.prototype = Object.create(CoroutineImpl.prototype);
  Coroutine$RevenueMonsterSDKTest$initSDK$lambda.prototype.constructor = Coroutine$RevenueMonsterSDKTest$initSDK$lambda;
  Coroutine$RevenueMonsterSDKTest$initSDK$lambda.prototype.doResume = function () {
    do
      try {
        switch (this.state_0) {
          case 0:
            this.state_0 = 2;
            this.result_0 = this.local$closure$sdk.payment.qrPay(this);
            if (this.result_0 === COROUTINE_SUSPENDED)
              return COROUTINE_SUSPENDED;
            continue;
          case 1:
            throw this.exception_0;
          case 2:
            return this.result_0;
          default:this.state_0 = 1;
            throw new Error('State Machine Unreachable execution');
        }
      } catch (e) {
        if (this.state_0 === 1) {
          this.exceptionState_0 = this.state_0;
          throw e;
        } else {
          this.state_0 = this.exceptionState_0;
          this.exception_0 = e;
        }
      }
     while (true);
  };
  function RevenueMonsterSDKTest$initSDK$lambda(closure$sdk_0) {
    return function ($receiver_0, continuation_0, suspended) {
      var instance = new Coroutine$RevenueMonsterSDKTest$initSDK$lambda(closure$sdk_0, $receiver_0, this, continuation_0);
      if (suspended)
        return instance;
      else
        return instance.doResume(null);
    };
  }
  RevenueMonsterSDKTest.prototype.initSDK = function () {
    var sdk = new RevenueMonsterSDK();
    assertEquals('test', sdk.getString());
    launch(CoroutineScope(coroutines.Dispatchers.Main), void 0, void 0, RevenueMonsterSDKTest$initSDK$lambda(sdk));
  };
  RevenueMonsterSDKTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'RevenueMonsterSDKTest',
    interfaces: []
  };
  var package$org = _.org || (_.org = {});
  var package$rm = package$org.rm || (package$org.rm = {});
  var package$sdk = package$rm.sdk || (package$rm.sdk = {});
  package$sdk.RevenueMonsterSDKTest = RevenueMonsterSDKTest;
  suite('org.rm.sdk', false, function () {
    suite('RevenueMonsterSDKTest', false, function () {
      test('initSDK', false, function () {
        return (new RevenueMonsterSDKTest()).initSDK();
      });
    });
  });
  Kotlin.defineModule('rm-kotlin-sdk-test', _);
  return _;
}));

//# sourceMappingURL=rm-kotlin-sdk-test.js.map
