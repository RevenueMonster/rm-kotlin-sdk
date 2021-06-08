(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'rm-kotlin-sdk'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'rm-kotlin-sdk'.");
    }root['rm-kotlin-sdk'] = factory(typeof this['rm-kotlin-sdk'] === 'undefined' ? {} : this['rm-kotlin-sdk'], kotlin);
  }
}(this, function (_, Kotlin) {
  'use strict';
  var print = Kotlin.kotlin.io.print_s8jyv4$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  function SDK() {
    print('debug here');
  }
  SDK.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'SDK',
    interfaces: []
  };
  var package$org = _.org || (_.org = {});
  var package$rm = package$org.rm || (package$org.rm = {});
  var package$sdk = package$rm.sdk || (package$rm.sdk = {});
  package$sdk.SDK = SDK;
  Kotlin.defineModule('rm-kotlin-sdk', _);
  return _;
}));

//# sourceMappingURL=rm-kotlin-sdk.js.map
