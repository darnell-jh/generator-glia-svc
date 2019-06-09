const path = require('path');
const assert = require('yeoman-assert');
const helpers = require('yeoman-test');
const expectedFiles = require('./utils/expected-files.js');

describe('Glia Service generator', () => {
  before((done) => {
    helpers
        .run(path.join(__dirname, '../generators/app'))
        .withOptions({
          'from-cli': true,
          'skipInstall': true,
          'skipChecks': true,
        })
        .withPrompts({
          projectName: 'MyProject',
          projectType: 'Command',
          packageName: 'com.example.app',
        })
        .on('end', done);
  });

  it('should create default files for commands', () => {
    assert.file(expectedFiles.command);
  });
});

