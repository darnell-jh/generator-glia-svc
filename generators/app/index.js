const Generator = require('yeoman-generator');

const tplCache = {};

// Capitalizes the first letter of a string.
String.prototype.capitalize = function() {
  if (!tplCache[this + '.capitalize']) {
    const words = this.toLocaleLowerCase();
    tplCache[this + '.capitalize'] =
     words.charAt(0).toLocaleUpperCase() + words.slice(1);
  }
  return tplCache[this + '.capitalize'];
};

module.exports = class extends Generator {
  /**
   * Provides prompts for scaffolding
   * @return {Promise}
   */
  prompting() {
    const prompts = [
      {
        type: 'input',
        name: 'projectName',
        message: '(1/5) What is the name of your project?',
        default: this.appname,
      },
      {
        type: 'list',
        name: 'projectType',
        message: '(2/5) What type of project are you creating?',
        choices: ['Command', 'Query', 'Worker'],
      },
      {
        type: 'input',
        name: 'packageName',
        message: '(3/5) What is your default package name?',
        default: 'com.example',
      },
      {
        type: 'input',
        name: 'serviceName',
        message: '(4/5) What do you want to call your service?' +
         '(Note: This will create *Service, *Controller, etc.)',
      },
      {
        type: 'input',
        name: 'binaryRepo',
        message: '(5/5) What is the binary repo manager destination? (Ex: jfrog, bintray)',
        default: 'dh',
      },
    ];

    return this.prompt(prompts).then((answers) => {
      this.answers = answers;
    });
  }

  /**
   * Write out files for scaffolding
   */
  writing() {
    this.packageFolder = this.answers['packageName'].replace(/\./g, '/');
    this._generateBase();
    const projectType = this.answers['projectType'];
    // console.log('Writing projectType: ', projectType);
    switch (projectType) {
      case 'Command':
        this._generateCmd();
        break;
      case 'Query':
        this._generateQuery();
        break;
    }
  }

  /**
   * Generates the base files for commands, services, and workers.
   */
  _generateBase() {
    const kotlinDir = 'src/main/kotlin/' + this.packageFolder + '/';
    const kotlinDirTmpl = 'base/src/main/kotlin/package/';

    // Copy gitignore
    this.fs.copyTpl(
        this.templatePath('base/gitignore'),
        this.destinationPath('.gitignore'),
        this.answers
    );

    // Dockerfile
    this.fs.copyTpl(
        this.templatePath('base/Dockerfile'),
        this.destinationPath('Dockerfile'),
        this.answers
    );

    // Config files
    this.fs.copyTpl(
        this.templatePath('base/*.*'),
        this.destinationPath(),
        this.answers
    );

    // Sources
    this.fs.copyTpl(
        this.templatePath(kotlinDirTmpl),
        this.destinationPath(kotlinDir),
        this.answers
    );
  }

  /**
   * Generate the files specific for Command services.
   */
  _generateCmd() {
    const kotlinDir = 'src/main/kotlin/' + this.packageFolder + '/';
    const kotlinDirTmpl = 'cmd/src/main/kotlin/package/';
    const kotlinTestDir = 'src/test/kotlin/' + this.packageFolder + '/';
    const kotlinTestDirTmpl = 'cmd/src/test/kotlin/package/';
    const kotlinResDir = 'src/main/resources/';
    const kotlinResTmpl = 'cmd/src/main/resources/';

    // Config files
    this.fs.copyTpl(
        this.templatePath('cmd/*.*'),
        this.destinationPath(),
        this.answers
    );

    // Application Config Files
    this.fs.copyTpl(
        this.templatePath(kotlinResTmpl),
        this.destinationPath(kotlinResDir),
        this.answers
    );

    // Test Files
    this.fs.copyTpl(
        this.templatePath(kotlinTestDirTmpl),
        this.destinationPath(kotlinTestDir),
        this.answers
    );

    // Cmd Sources
    this.fs.copyTpl(
        this.templatePath(kotlinDirTmpl),
        this.destinationPath(kotlinDir),
        this.answers
    );
  }

  /**
   * Generate the files specific for Query services.
   */
  _generateQuery() {
    const kotlinDir = 'src/main/kotlin/' + this.packageFolder + '/';
    const kotlinDirTmpl = 'query/src/main/kotlin/package/';
    const kotlinTestDir = 'src/test/kotlin/' + this.packageFolder + '/';
    const kotlinTestDirTmpl = 'query/src/test/kotlin/package/';
    const kotlinResDir = 'src/main/resources/';
    const kotlinResTmpl = 'query/src/main/resources/';

    // Config files
    this.fs.copyTpl(
        this.templatePath('query/*.*'),
        this.destinationPath(),
        this.answers
    );

    // Application Config Files
    this.fs.copyTpl(
        this.templatePath(kotlinResTmpl),
        this.destinationPath(kotlinResDir),
        this.answers
    );

    // Test Files
    this.fs.copyTpl(
        this.templatePath(kotlinTestDirTmpl),
        this.destinationPath(kotlinTestDir),
        this.answers
    );

    // Query Sources
    this.fs.copyTpl(
        this.templatePath(kotlinDirTmpl),
        this.destinationPath(kotlinDir),
        this.answers
    );
  }
};
