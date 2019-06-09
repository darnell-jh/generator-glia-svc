const Generator = require('yeoman-generator');

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
        message: '(1/3) What is the name of your project?',
        default: this.appname,
      },
      {
        type: 'list',
        name: 'projectType',
        message: '(2/3) What type of project are you creating?',
        choices: ['Command', 'Query', 'Worker'],
      },
      {
        type: 'input',
        name: 'packageName',
        message: '(3/3) What is your default package name?',
        default: 'com.example',
      },
    ];

    return this.prompt(prompts).then((answers) => {
      this.packageName = answers.packageName;
      this.projectName = answers.projectName;
      this.projectType = answers.projectType;
    });
  }

  /**
   * Write out files for scaffolding
   */
  writing() {
    this.packageFolder = this.packageName.replace(/\./g, '/');
    this._generateBase();
    console.log('projectType: ', this.projectType);
    switch (this.projectType) {
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
        {
          packageName: this.packageName,
          projectName: this.projectName,
        }
    );

    // Config files
    this.fs.copyTpl(
        this.templatePath('base/*.*'),
        this.destinationPath(),
        {
          packageName: this.packageName,
          projectName: this.projectName,
        }
    );

    // Sources
    this.fs.copyTpl(
        this.templatePath(kotlinDirTmpl),
        this.destinationPath(kotlinDir),
        {
          packageName: this.packageName,
          projectName: this.projectName,
        }
    );
  }

  /**
   * Generate the files specific for Command services.
   */
  _generateCmd() {
    const kotlinTestDir = 'src/test/kotlin/' + this.packageFolder + '/';
    const kotlinTestDirTmpl = 'cmd/src/test/kotlin/package/';
    const kotlinResDir = 'src/main/resources/';
    const kotlinResTmpl = 'cmd/src/main/resources/';

    // Application Config Files
    this.fs.copyTpl(
        this.templatePath(kotlinResTmpl),
        this.destinationPath(kotlinResDir),
        {
          packageName: this.packageName,
          projectName: this.projectName,
        }
    );

    // Test Files
    this.fs.copyTpl(
        this.templatePath(kotlinTestDirTmpl),
        this.destinationPath(kotlinTestDir),
        {
          packageName: this.packageName,
          projectName: this.projectName,
        }
    );
  }

  /**
   * Generate the files specific for Query services.
   */
  _generateQuery() {
    const kotlinTestDir = 'src/test/kotlin/' + this.packageFolder + '/';
    const kotlinTestDirTmpl = 'query/src/test/kotlin/package/';
    const kotlinResDir = 'src/main/resources/';
    const kotlinResTmpl = 'query/src/main/resources/';

    // Application Config Files
    this.fs.copyTpl(
        this.templatePath(kotlinResTmpl),
        this.destinationPath(kotlinResDir),
        {
          packageName: this.packageName,
          projectName: this.projectName,
        }
    );

    // Test Files
    this.fs.copyTpl(
        this.templatePath(kotlinTestDirTmpl),
        this.destinationPath(kotlinTestDir),
        {
          packageName: this.packageName,
          projectName: this.projectName,
        }
    );
  }
};
