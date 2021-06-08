(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'rm-kotlin-sdk', 'kotlin-test'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('rm-kotlin-sdk'), require('kotlin-test'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk-test'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'rm-kotlin-sdk-test'.");
    }if (typeof this['rm-kotlin-sdk'] === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk-test'. Its dependency 'rm-kotlin-sdk' was not found. Please, check whether 'rm-kotlin-sdk' is loaded prior to 'rm-kotlin-sdk-test'.");
    }if (typeof this['kotlin-test'] === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk-test'. Its dependency 'kotlin-test' was not found. Please, check whether 'kotlin-test' is loaded prior to 'rm-kotlin-sdk-test'.");
    }root['rm-kotlin-sdk-test'] = factory(typeof this['rm-kotlin-sdk-test'] === 'undefined' ? {} : this['rm-kotlin-sdk-test'], kotlin, this['rm-kotlin-sdk'], this['kotlin-test']);
  }
}(this, function (_, Kotlin, $module$rm_kotlin_sdk, $module$kotlin_test) {
  'use strict';
  var SDK = $module$rm_kotlin_sdk.org.rm.sdk.SDK;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var test = $module$kotlin_test.kotlin.test.test;
  var suite = $module$kotlin_test.kotlin.test.suite;
  function SDKTest() {
  }
  SDKTest.prototype.testEncodeToString = function () {
    new SDK();
  };
  SDKTest.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SDKTest',
    interfaces: []
  };
  var package$org = _.org || (_.org = {});
  var package$rm = package$org.rm || (package$org.rm = {});
  var package$sdk = package$rm.sdk || (package$rm.sdk = {});
  package$sdk.SDKTest = SDKTest;
  suite('org.rm.sdk', false, function () {
    suite('SDKTest', false, function () {
      test('testEncodeToString', false, function () {
        return (new SDKTest()).testEncodeToString();
      });
    });
  });
  Kotlin.defineModule('rm-kotlin-sdk-test', _);
  return _;
}));

//# sourceMappingURL=rm-kotlin-sdk-test.js.map
